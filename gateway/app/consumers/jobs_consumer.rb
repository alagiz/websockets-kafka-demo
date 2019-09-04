class JobsConsumer < ApplicationConsumer
  # Consumes given messages
  def consume
    job = params.slice('userId', 'jobId', 'jobStep', 'isJobDone')
    conn = Faraday.new('http://localhost:80')

    conn.post do |req|
      req.url '/notify_job_status'
      req.body = job.to_json
      req.headers['Content-Type'] = 'application/json'
    end

  end
end