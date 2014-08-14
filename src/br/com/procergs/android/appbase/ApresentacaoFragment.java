package br.com.procergs.android.appbase;

import br.gov.rs.procergs.android.appbase.R;
import android.content.Intent;
import android.net.MailTo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ApresentacaoFragment extends Fragment{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	final View v = inflater.inflate(R.layout.apresentacao_fragment, container, false);

		WebView wv = (WebView) v.findViewById(R.id.wvApresentacao);
        wv.setWebViewClient(new WebViewClient() {
        	@Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
        		if (url.startsWith("mailto:")) {
        			MailTo mt = MailTo.parse(url);
        			Intent i = new Intent(Intent.ACTION_SEND);
        			//i.setType("text/plain");
        			i.setType("message/rfc822");
        			i.putExtra(Intent.EXTRA_EMAIL, new String[] { mt.getTo() });
        			i.putExtra(Intent.EXTRA_SUBJECT, mt.getSubject());
        			i.putExtra(Intent.EXTRA_CC, mt.getCc());
        			i.putExtra(Intent.EXTRA_TEXT, mt.getBody());
        			getActivity().startActivity(i);
        			view.reload();
        			return true;
        		}
                view.loadUrl(url);
                return true;
            }
        });
        wv.loadUrl("file:///android_asset/html/index.html");
    	
        return v;
        
    }
    
}
