FROM node:9.6.1

RUN mkdir /usr/src/app
WORKDIR /usr/src/app
COPY /public /usr/src/app/public
COPY /src /usr/src/app/src

ENV PATH /usr/src/app/node_modules/.bin:$PATH

COPY package.json /usr/src/app/package.json

RUN npm install --silent