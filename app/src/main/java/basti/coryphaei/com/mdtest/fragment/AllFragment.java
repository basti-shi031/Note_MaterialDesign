package basti.coryphaei.com.mdtest.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.ArrayList;
import java.util.List;

import basti.coryphaei.com.mdtest.AddNoteActivity;
import basti.coryphaei.com.mdtest.R;
import basti.coryphaei.com.mdtest.application.App;
import basti.coryphaei.com.mdtest.helper.OnStartDragListener;
import basti.coryphaei.com.mdtest.helper.SimpleItemTouchHelperCallback;
import basti.coryphaei.com.mdtest.utils.Constant;
import basti.coryphaei.com.mdtest.utils.NoteAdapter;
import de.greenrobot.dao.query.Query;
import greendao.utils.Note;
import greendao.utils.NoteDao;
import greendao.utils.Seq;
import greendao.utils.SeqDao;

/**
 * Created by Bowen on 2015/10/11.
 */
public class AllFragment extends Fragment implements OnStartDragListener,NoteAdapter.NoteListener{

    private RecyclerView mRecyclerView;
    private FloatingActionButton mAddButton;
    private List<Note> mNotes,all;
    private NoteAdapter mAdapter;
    private App myApp;
    private NoteDao mNoteDao;
    private SeqDao mSeqIdDao;
    private boolean isFirstEnter = true;
    private ItemTouchHelper mItemTouchHelper;
    private int size = 0;
    private String mOrderString = "";
    private List<Integer> mOrderList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (container == null)
        {
            // Currently in a layout without a contaifirstpage.xmlner, so no
            // reason to create our view.
            return null;
        }
        View view = inflater.inflate(R.layout.allfragment, container, false);
        initView(view);
        initDates();
        initEvents();
        return view;
    }

    private void initEvents() {

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddNoteActivity.class);
                intent.putExtra(Constant.FUNC, Constant.ADD);
                ActivityOptionsCompat options = ActivityOptionsCompat
                        .makeSceneTransitionAnimation(getActivity(), mAddButton, "background");
                getActivity().startActivity(intent, options.toBundle());
                //addNote();
            }
        });
    }


    private void searchNote() {
        size = all.size();
        mNotes.clear();
        all.clear();
        Query query = mNoteDao.queryBuilder()
                .orderDesc(NoteDao.Properties.CreateDate)
                .build();

//      查询结果以 List 返回
        mNotes = query.list();
        all.addAll(mNotes);

        if (all.size()>size){
            //说明增加了数据
            mAdapter.notifyItemInserted(0);
        }else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private void searchOrder() {
        mOrderList.clear();

        Query query = mSeqIdDao.queryBuilder().build();

        if (query.list().size() > 0)
        mOrderString = ((ArrayList<Seq>)query.list()).get(0).getOrderId();

        if (!TextUtils.isEmpty(mOrderString)){
            String[] list = mOrderString.split(",");
            for(String string : list){
                Integer orderId = Integer.parseInt(string);
                mOrderList.add(orderId);
            }
        }
    }

    private void initDates() {

        myApp = (App) getActivity().getApplicationContext();
        mNotes = new ArrayList<>();
        mOrderList = new ArrayList<>();
        all = new ArrayList<>();
        mAdapter = new NoteAdapter(all,mOrderList,getActivity(),this);
        mAdapter.setNoteListener(this);
        mRecyclerView.setAdapter(mAdapter);
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);

        mNoteDao = myApp.getNoteDao();
        mSeqIdDao = myApp.getOrderIdDao();
        String textColumn = NoteDao.Properties.CreateDate.columnName;
        String orderBy = textColumn + " COLLATE LOCALIZED ASC";
        myApp.cursor = myApp.db.query(mNoteDao.getTablename(), mNoteDao.getAllColumns(), null, null, null, null, orderBy);
        myApp.cursor = myApp.db.query(mSeqIdDao.getTablename(), mSeqIdDao.getAllColumns(), null, null, null, null, null);

        searchNote();
        searchOrder();


        mAdapter.notifyDataSetChanged();
    }


    private void initView(View view) {

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        // 设置LinearLayoutManager
        LinearLayoutManager layout = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layout);
        // 设置ItemAnimator
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // 设置固定大小
        mRecyclerView.setHasFixedSize(true);
        // 为mRecyclerView设置适配器
        mAddButton = (FloatingActionButton) view.findViewById(R.id.add_button);
        Animation animation = AnimationUtils.loadAnimation(getActivity(),R.anim.fab_in1);
        mAddButton.startAnimation(animation);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (!isFirstEnter){
            mRecyclerView.scrollToPosition(0);
            searchNote();
        }

        isFirstEnter = false;

    }


    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }

    @Override
    public void onItemRemoved() {
        size--;
    }
}
