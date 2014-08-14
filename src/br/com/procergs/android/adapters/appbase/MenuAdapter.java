/**
 * Classe responsável pela montagem do menu
 * de navegação no padrão de Navigation Drawer
 * 
 * Esta classe recebe parâmetros do controller para 
 * designar seus itens de menu e colocá-los dentro de
 * uma estrutura que alimenta o Navigation Drawer
 * 
 * @author evertoncanez
 */

package br.com.procergs.android.adapters.appbase;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.Log;
import br.gov.rs.procergs.android.appbase.R;
import br.gov.rs.procergs.prandroid.list.item.EntryItem;
import br.gov.rs.procergs.prandroid.list.item.Item;
import br.gov.rs.procergs.prandroid.list.item.SectionItem;
import br.gov.rs.procergs.prandroid.adapters.EntryAdapter;

public class MenuAdapter {
	
	private Context context;
	private TypedArray icones;
	private String[] nomes;
	private ArrayList<Item> items;
	
	/**
	 * Construtor para o carregamento
	 * dos itens de menu existentes no
	 * arquivo de resources chamado arrays.xml
	 * @param ctx
	 */
	public MenuAdapter(Context ctx) {
		this.context = ctx;
		this.nomes   = this.context.getResources().getStringArray(R.array.menu_itens);
		this.icones  = this.context.getResources().obtainTypedArray(R.array.menu_icones);
		icones.recycle();
	}
	
	/**
	 * Método para montagem do menu
	 * que será exibido na navegação 
	 * do app
	 * 
	 * @return Um objeto EntryAdapter
	 * 
	 * EntryAdapter é o objeto responsável
	 * pela manipulação da view
	 */
	public EntryAdapter getXmlAdapter(){
		
		this.items	 = new ArrayList<Item>();
		
		items.add(new SectionItem("MENU"));
		items.add(getEntryItem(0));
		items.add(getEntryItem(1));
		items.add(getEntryItem(2));
		items.add(getEntryItem(3));
		items.add(getEntryItem(4));
		
		items.add(new SectionItem("EXEMPLOS"));
		items.add(getEntryItem(5));
		items.add(getEntryItem(6));
		items.add(getEntryItem(7));
		
		EntryAdapter adapter = new EntryAdapter(this.context, items);

		return adapter;
	}
	
	public EntryAdapter getStringAdapter(){
		
		this.items = new ArrayList<Item>();
		
		items.add(new SectionItem("MENU"));
		items.add(getEntryItem(0, 	R.string.menu_item_0, 	android.R.drawable.ic_menu_info_details));
		items.add(getEntryItem(1, 	R.string.menu_item_1, 	android.R.drawable.ic_menu_agenda));
		items.add(getEntryItem(2, 	R.string.menu_item_2, 	android.R.drawable.ic_menu_compass));
		items.add(getEntryItem(3, 	R.string.menu_item_3, 	android.R.drawable.ic_menu_manage));
		items.add(getEntryItem(4, 	R.string.menu_item_4, 	android.R.drawable.ic_menu_manage));
		items.add(getEntryItem(5, 	R.string.menu_item_5, 	android.R.drawable.ic_menu_manage));
		items.add(getEntryItem(6, 	R.string.menu_item_6, 	android.R.drawable.ic_menu_manage));
		items.add(getEntryItem(6, 	R.string.menu_item_7, 	android.R.drawable.ic_menu_manage));
		
		EntryAdapter adapter = new EntryAdapter(this.context, items);

		return adapter;
		
	}

	/**
	 * Método responsável pela montagem
	 * do menu a partir do arquivo de resources
	 * arrays.xml
	 * 
	 * @param index
	 * 
	 * O parâmetro index é o identificador que indica
	 * a posição do item de menu. Dentro da sua estrutura,
	 * é este o parâmetro que irá indicar qual ação
	 * deve ser chamada quando um item for acionado.
	 * 
	 * @return EntryItem
	 */
	private EntryItem getEntryItem(int index) {
		int iconesResId = icones.getResourceId(index, R.drawable.ic_launcher);
		Log.d("ITEM", String.valueOf(iconesResId));
		EntryItem entryItem = new EntryItem(index, nomes[index], iconesResId);
		return entryItem;
	}

	/**
	 * Método para montar o menu sem o uso
	 * do arquivo de resources arrays.xml
	 * 
	 * Os itens devem ser acrescentados na 
	 * estrutura de lista diretamente no 
	 * código
	 * 
	 * @param index
	 * @param resItem
	 * @param drawableIconId
	 * @return
	 */
	private EntryItem getEntryItem(int index, int resItem, int drawableIconId) {
		Drawable icone = this.context.getResources().getDrawable(drawableIconId);
		String item  = this.context.getResources().getString(resItem);
		EntryItem entryItem = new EntryItem(index, item, icone);
		return entryItem;
	}

	
}
