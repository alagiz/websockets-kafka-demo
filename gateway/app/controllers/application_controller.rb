class ApplicationController < ActionController::API
  include Response

  def handle_options_request
    head(:ok) if request.request_method == "OPTIONS"
  end

  def jwt_valid?
    conn = Faraday.new(url: Settings.authentication_service)

    response = conn.get '/validate_jwt' do |req|
      req.headers['Authorization'] = request.headers['Authorization']
    end

    response.status == 200
  end
end
