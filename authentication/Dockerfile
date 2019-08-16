FROM ruby:2.5.3

RUN apt-get update -qq && apt-get install -y build-essential libpq-dev nodejs && gem install bundler
RUN mkdir /authentication
WORKDIR /authentication

COPY Gemfile /authentication/Gemfile
COPY Gemfile.lock /authentication/Gemfile.lock

RUN bundle install

COPY . /authentication