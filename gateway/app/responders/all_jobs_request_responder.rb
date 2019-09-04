# frozen_string_literal: true

class AllJobsRequestResponder < ApplicationResponder
  topic 'queueing.job.all'

  def respond(event_payload)
    respond_to 'queueing.job.all', event_payload
  end
end
