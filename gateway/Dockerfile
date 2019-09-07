# ------ build container ------
FROM ruby:2.5.3-alpine AS rails-build

ARG RAILS_ROOT=/app
ARG BUILD_PACKAGES="build-base curl-dev"
ARG DEV_PACKAGES="yaml-dev zlib-dev"
ARG RUBY_PACKAGES="tzdata"

ENV RAILS_ENV=production
ENV SECRET_KEY_BASE=production_test_key
ENV NODE_ENV=production
ENV BUNDLE_APP_CONFIG="$RAILS_ROOT/.bundle"

WORKDIR $RAILS_ROOT

# install packages
RUN apk update \
    && apk upgrade \
    && apk add --update --no-cache $BUILD_PACKAGES $DEV_PACKAGES $RUBY_PACKAGES

COPY Gemfile* package.json ./
RUN bundle config --global frozen 1 \
    && bundle install --without development:test --path=vendor/bundle \
    # remove unneeded files (cached *.gem, *.o, *.c)
    && rm -rf vendor/bundle/ruby/2.5.0/cache/*.gem \
    && find vendor/bundle/ruby/2.5.0/gems/ -name "*.c" -delete \
    && find vendor/bundle/ruby/2.5.0/gems/ -name "*.o" -delete
COPY . .

# remove folders not needed in resulting image
RUN rm -rf tmp/cache app/assets vendor/assets spec

# ------ run container ------
FROM ruby:2.5.3-alpine
ARG RAILS_ROOT=/app
ARG PACKAGES="tzdata bash"

ENV RAILS_ENV=production
ENV SECRET_KEY_BASE=production_test_key
ENV BUNDLE_APP_CONFIG="$RAILS_ROOT/.bundle"

WORKDIR $RAILS_ROOT

# install packages
RUN apk update \
    && apk upgrade \
    && apk add --update --no-cache $PACKAGES

COPY --from=rails-build $RAILS_ROOT $RAILS_ROOT

