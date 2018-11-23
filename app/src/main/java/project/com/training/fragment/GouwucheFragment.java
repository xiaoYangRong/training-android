package project.com.training.fragment;
import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import project.com.training.R;
import project.com.training.adapter.GouWuCheAdapter;
import project.com.training.adapter.GouWuCheCursorApdater;

import project.com.training.adapter.ShouCangCursorApdater;
import project.com.training.adapter.ShouYeAdapter;
import project.com.training.dao.CollectDao;
import project.com.training.dao.ShoppingDao;
import project.com.training.model.User;


public class GouwucheFragment extends Fragment {

    private ListView listView;

    public GouwucheFragment() {
        // Required empty public constructor
    }

    public User setUser(Long user_id) {
        User user = new User();
        user.setId(user_id);
        return user;

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_gou_wu_che, container, false);
        //List<Map<String, Object>> list = getData();
        List list=getData();
        listView = view.findViewById(R.id.gouwuche_item);
        ShoppingDao shoppingDao = new ShoppingDao(getActivity());
        Cursor cursor = shoppingDao.findMyGouWuChe();
        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) {
            //adapter = new StudentCursorApdater(this, cursor);
           /* GouWuCheSimpleCursorAdapter gouWuCheSimpleCursorAdapter=new GouWuCheSimpleCursorAdapter(
                    this,
                    R.id.gouwuche_item,
                    cursor,
                    new String[]{
                            cursor.getString(cursor.getColumnIndex("name")),
                                    cursor.getString(cursor.getColumnIndex("jhi_number")),
                                            cursor.getString(cursor.getColumnIndex("price")),
                                                    cursor.getString(cursor.getColumnIndex("num"))},
                    new int[]{
                            R.id.name,R.id.number,R.id.price,R.id.num
                    });*/

            GouWuCheCursorApdater gouWuCheCursorApdater = new GouWuCheCursorApdater(getActivity(), cursor);
           listView.setAdapter(gouWuCheCursorApdater);
            Log.d("cc", cursor.toString());
        }
        // listView.setAdapter(new GouWuCheAdapter(getActivity(), list));
        setListViewHeightBasedOnChildren(listView);
        return view;
    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }






   /* public List<Map<String, Object>> getData(){
        List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
        for (int i = 0; i < 10; i++) {
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("picture", R.drawable.book);
            map.put("name", "book"+i);
            map.put("price", +i);
            map.put("num",+i);
            map.put("jhi_number","编号"+i);
            list.add(map);
        }
        return list;
//    }*/
}



