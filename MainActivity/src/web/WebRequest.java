package web;
import util.JsonReader;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class WebRequest {
	private String url; 
	private AsyncHttpResponseHandler handler; 
	private JsonReader reader; 
	public CallbackWrapper callbackWrapper; 
	public RequestWrapper requestWrapper; 
	private ProgressDisplay progressDisplay = null;
	
	public WebRequest(String u, AsyncHttpResponseHandler h) {
		setUrl(u); setHandler(h); 
		this.setRequestWrapper();
	}

	public WebRequest(String u, AsyncHttpResponseHandler h, CallbackWrapper c) {
		setUrl(u);
		setHandler(h);
		callbackWrapper = c; 
	}
	
	void setRequestWrapper() {
		requestWrapper = new RequestWrapper() {
			@Override
			public int executeRequest() {
				WebClient.get(url, handler);
				return 0;
			}
		};
		if (callbackWrapper == null) {
			callbackWrapper = new CallbackWrapper() {
				@Override
				public void render() {
					return; 
					// TODO Auto-generated method stub
				}
			};
		}
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public AsyncHttpResponseHandler getHandler() {
		return handler;
	}
	public void setHandler(AsyncHttpResponseHandler handler) {
		this.handler = handler;
	}
	public ProgressDisplay getProgressDisplay() {
		return progressDisplay;
	}
	public void setProgressDisplay(ProgressDisplay progressDisplay) {
		this.progressDisplay = progressDisplay;
	}
	public JsonReader getReader() {
		return reader;
	}
	public void setReader(JsonReader reader) {
		this.reader = reader;
	}
	
//	public void setToken(Token t) {
//		this.token = t; 
//		this.setAuthenticationHeader(token);
//	}
//	
//	public void sendLoginNotification() {
//		
//	}
//	
//	public void signIn(JSONObject params, String url) throws UnsupportedEncodingException, JSONException, InvalidParametersException {
//		setTokenHandler(new TokenHandler(context, params, url, this )); 
//	}
}
