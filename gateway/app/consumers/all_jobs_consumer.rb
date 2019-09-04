class AllJobsConsumer < ApplicationConsumer
  # Consumes given messages
  def consume
    job = params.slice('userId', 'jobList')
    conn = Faraday.new('http://localhost:80')

    conn.post do |req|
      req.url '/notify_jobs'
      req.body = job.to_json
      req.headers['Content-Type'] = 'application/json'
    end
  end
end