FROM node:18

WORKDIR /app

COPY ./streaming .
RUN echo "MUSIC_API_LINK=http://ptvu-be-music:8080/music" > .env
RUN npm install

EXPOSE 8000
CMD ["node", "server.js"]