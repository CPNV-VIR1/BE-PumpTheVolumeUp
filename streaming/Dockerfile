FROM node:18

WORKDIR /app

COPY ./* ./
RUN echo "MUSIC_API_LINK=http://ptvu-be-music:8080/musics" > .env
RUN npm install

EXPOSE 8000
CMD ["node", "server.js"]