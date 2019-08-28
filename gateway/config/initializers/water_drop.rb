WaterDrop.setup do |config|
  config.deliver = true
  config.kafka.seed_brokers = %w[kafka://kafka:9092]
end