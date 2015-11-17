package com.macalyou.murisly.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import com.macalyou.murisly.listview.HeaderListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ListView lvShow;

    private String[] names = new String[]
            { "虎头", "弄玉", "李清照", "李白","虎头1", "弄玉1", "李清照1", "李白1"};
    private String[] descs = new String[]
            { "可爱的小孩", "一个擅长音乐的女孩", "一个擅长文学的女性", "浪漫主义诗人","可爱的小孩", "一个擅长音乐的女孩", "一个擅长文学的女性", "浪漫主义诗人"};
    private int[] imageIds = new int[]
            { R.drawable.tiger , R.drawable.nongyu, R.drawable.qingzhao , R.drawable.libai,R.drawable.tiger , R.drawable.nongyu, R.drawable.qingzhao , R.drawable.libai};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvShow = (ListView)findViewById(R.id.lvsoftname);
        final PinableFrameLayout pinableFrameLayout = (PinableFrameLayout)findViewById(R.id.pf_fram);
        pinableFrameLayout.initFrame();
        //pinableFrameLayout.setPinnedView(lvShow);
/*
        lvShow.setOnClickListener(new AbsListView.OnScrollListener(){
            public void onScrollStateChanged(AbsListView view, int scrollState) {};

            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                pinableFrameLayout.onScroll();
            }
        });
*/

        // 创建一个List集合，List集合的元素是Map
        List<Map<String, Object>> listItems =
                new ArrayList<Map<String, Object>>();
        for (int i = 0; i < names.length; i++)
        {
            Map<String, Object> listItem = new HashMap<String, Object>();
            listItem.put("header", imageIds[i]);
            listItem.put("personName", names[i]);
            listItem.put("desc", descs[i]);
            listItems.add(listItem);
        }
        // 创建一个SimpleAdapter
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems,
                R.layout.simple_item,
                new String[] { "personName", "header" , "desc"},
                new int[] { R.id.name, R.id.header , R.id.desc });
        ListView list = (ListView) findViewById(R.id.lvsoftname);
        // 为ListView设置Adapter
        list.setAdapter(simpleAdapter);

        // 为ListView的列表项的单击事件绑定事件监听器
        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            // 第position项被单击时激发该方法
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id)
            {
                System.out.println(names[position]
                        + "被单击了");
            }
        });
        // 为ListView的列表项的选中事件绑定事件监听器
        list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            // 第position项被选中时激发该方法
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id)
            {
                System.out.println(names[position]
                        + "被选中了");
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
