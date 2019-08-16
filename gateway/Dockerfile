FROM ruby:2.5.3

RUN apt-get update -qq && apt-get install -y build-essential libpq-dev nodejs && gem install bundler
RUN mkdir /gateway
WORKDIR /gateway

COPY Gemfile /gateway/Gemfile
COPY Gemfile.lock /gateway/Gemfile.lock

RUN bundle install

COPY . /gateway