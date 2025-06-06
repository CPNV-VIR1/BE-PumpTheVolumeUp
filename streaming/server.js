require('dotenv').config();
const express = require('express');
const axios = require('axios');
const fs = require('fs');
const app = express();
const port = 8000;

app.get('/stream/:id', async (req, res) => {
    try {
        const infoResponse = await axios.get(`${process.env.MUSIC_API_LINK}/${req.params.id}`);
        const filePath = infoResponse.data.path;

        fs.stat(filePath, (err, stats) => {
            if (err) return res.status(404).send('Music not found');

            const range = req.headers.range;
            if (!range) return res.status(416).send('Range header required');

            const positions = range.replace(/bytes=/, "").split("-");
            const start = parseInt(positions[0], 10);
            const total = stats.size;
            const end = positions[1] ? parseInt(positions[1], 10) : total - 1;
            const chunksize = (end - start) + 1;

            res.writeHead(206, {
                "Content-Range": `bytes ${start}-${end}/${total}`,
                "Accept-Ranges": "bytes",
                "Content-Length": chunksize,
                "Content-Type": "audio/mpeg"
            });

            const stream = fs.createReadStream(filePath, { start, end });
            stream.pipe(res);
        });
    } catch (error) {
        res.status(500).send(error.message);
    }
});

app.listen(port, () => {
    console.log('Server running on port ' + port);
});
