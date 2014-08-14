package br.com.procergs.android.appbase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import br.com.procergs.android.adapters.appbase.MenuAdapter;
import br.com.procergs.android.navigation.appbase.PRNavigationDrawer;
import br.gov.rs.procergs.android.appbase.R;
import br.gov.rs.procergs.prandroid.adapters.EntryAdapter;
import br.gov.rs.procergs.prandroid.utils.Alert;

public class MainActivity extends FragmentActivity {

	private static final String SEPARADOR = System.getProperty("line.separator");
	protected static final String TAG = MainActivity.class.getSimpleName();

	private String[] namesMenu;
	private String[] classesMenu;
	private DrawerLayout drawerLayout;
	private ListView drawerListView;
	private PRNavigationDrawer drawer;
	private EntryAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		adapter = new MenuAdapter(this).getStringAdapter();
		namesMenu = getResources().getStringArray(R.array.menu_itens);
		classesMenu = getResources().getStringArray(R.array.menu_classes);
		drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
		drawerListView = (ListView) findViewById(R.id.drawer_list_view);
		
		drawer = new PRNavigationDrawer(this, drawerListView, namesMenu, classesMenu, adapter, drawerLayout);
		drawer.openDrawer();
		drawer.drawerItemClickListener();
		
		if (savedInstanceState == null){
			drawer.setContent(0);
		}
	}
	
	/**
	 * IMPORTANTE! O método syncState sincroniza o estado do DrawerLayout apos
	 * um onRestoreInstanceState
	 */
	@Override
	public void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		drawer.getDrawerToggle().syncState();
	}
	
	/**
	 * Tratamento do item selecionado no menu da action bar
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		/**
		 * Habilita o botão de navegação do Navigation Drawer situado à esquerda
		 * superior da action bar
		 */
		drawer.getDrawerToggle().onOptionsItemSelected(item);

		switch (item.getItemId()) {
		case R.id.menu_item_share:
			Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
			sharingIntent.setType("text/plain");
			String shareBody = "Endereço no SVN: svn.procergs.reders/AMP_Base_1.0";
			sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Repositório APM Base 1.0 no SVN");
			sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
			startActivity(Intent.createChooser(sharingIntent, getResources().getText(R.id.menu_item_share)));
			break;
		case R.id.menu_ajuda:
			drawer.setContent(new AjudaFragment(), item.toString());
			break;
		case R.id.menu_sobre:
			showAboutDialog();
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	
	/**
	 * Exemplo de dialog utilizando o método utilitário Alert para mostrar o
	 * item Sobre
	 */
	public void showAboutDialog() {
		StringBuffer msg = new StringBuffer();
		msg.append("PROCERGS Mobile © 2013 ");
		msg.append(SEPARADOR);
		msg.append(SEPARADOR);
		msg.append("v1.0");
		msg.append(SEPARADOR);
		msg.append(SEPARADOR);
		msg.append("DTI/STD");

		Alert.show(this, R.string.action_sobre, msg.toString());
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

}
