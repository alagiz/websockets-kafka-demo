Rails.application.routes.draw do
  # For details on the DSL available within this file, see http://guides.rubyonrails.org/routing.html
  match '*path', :controller => 'application', :action => 'handle_options_request', :via => :options
  resources :authenticate, only: %i[index show create]
  resources :validate_jwt, only: %i[index show]
end
