package br.com.procergs.android.appbase;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import br.com.procergs.android.adapters.appbase.CustomSimpleAdapter;
import br.gov.rs.procergs.android.appbase.R;

public class ChangelogFragment extends Fragment {

	static ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View v = inflater.inflate(R.layout.changelog_fragment, container,false);
		ListView lista = (ListView) v.findViewById(R.id.listaNotas); 

		String[] from = new String[] { "nota", "data",	"equipe" };
		int[] to   = new int[] { R.id.text1, R.id.text2,R.id.text3 };
		CustomSimpleAdapter adapter = new CustomSimpleAdapter(getActivity(), list, R.layout.custom_row_view, from, to);

		if (list.size() == 0)
			populateList(v);
		
		lista.setAdapter(adapter);

		return v;

	}

	private void populateList(View v) {
		ArrayAdapter<CharSequence> resAdapter = ArrayAdapter.createFromResource(v.getContext(), R.array.notas, android.R.layout.simple_spinner_item);
		HashMap<String, String> temp = new HashMap<String, String>();
		temp.put("nota", resAdapter.getItem(0).toString());
		temp.put("data", "31/08/2013");
		temp.put("equipe", "DTI/STD - Equipe Mobile");
		list.add(temp);
		HashMap<String, String> temp0 = new HashMap<String, String>();
		temp0.put("nota", resAdapter.getItem(1).toString());
		temp0.put("data", "30/08/2013");
		temp0.put("equipe", "DTI/STD - Equipe Mobile");
		list.add(temp0);
		HashMap<String, String> temp1 = new HashMap<String, String>();
		temp1.put("nota", resAdapter.getItem(2).toString());
		temp1.put("data", "30/08/2013");
		temp1.put("equipe", "DTI/STD - Equipe Mobile");
		list.add(temp1);
		HashMap<String, String> temp2 = new HashMap<String, String>();
		temp2.put("nota", resAdapter.getItem(3).toString());
		temp2.put("data", "30/08/2013");
		temp2.put("equipe", "DTI/STD - Equipe Mobile");
		list.add(temp2);
	}

}
