package basti.coryphaei.com.mdtest.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import basti.coryphaei.com.mdtest.AddNoteActivity;
import basti.coryphaei.com.mdtest.R;
import basti.coryphaei.com.mdtest.application.App;
import basti.coryphaei.com.mdtest.helper.ItemTouchHelperAdapter;
import basti.coryphaei.com.mdtest.helper.ItemTouchHelperViewHolder;
import basti.coryphaei.com.mdtest.helper.OnStartDragListener;
import greendao.utils.Note;
import greendao.utils.NoteDao;

/**
 * Created by Bowen on 2015/10/11.
 */
public class NoteAdapter extends RecyclerView.Adapter<ViewHolder> implements ItemTouchHelperAdapter {

    private List<Note> mNotes;
    private Context mContext;
    private final OnStartDragListener mDragStartListener;
    private NoteDao mNoteDao;
    private App myApp;
    private NoteListener mNoteListener;
    private List<Integer> mOrderList;
    public NoteAdapter(List<Note> list,List<Integer> orderList,Context context,OnStartDragListener dragStartListener){
        mContext = context;
        mNotes = list;
        mDragStartListener = dragStartListener;
        myApp = (App) context.getApplicationContext();
        mNoteDao = myApp.getNoteDao();
        mOrderList = orderList;

    }

    public interface NoteListener {
        void onItemRemoved();
    }

    public void setNoteListener(NoteListener noteListener){
        mNoteListener = noteListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        Note note = mNotes.get(i);

        viewHolder.title.setText(note.getTitle());
        viewHolder.content.setText(note.getContent());

        viewHolder.title.setTypeface(FontUtils.getBlackMiddle(mContext));
        viewHolder.content.setTypeface(FontUtils.getBlack(mContext));

        viewHolder.itemView.setBackgroundColor(Color.parseColor(myApp.Colors[note.getBackgroundColor()]));
        viewHolder.itemView.setTag(R.id.itemview_back,myApp.Colors[note.getBackgroundColor()]);
        initEvent(viewHolder, note);
    }

    private void initEvent(final ViewHolder viewHolder, final Note note) {

       viewHolder.note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AddNoteActivity.class);
                intent.putExtra("note",note);
                intent.putExtra(Constant.FUNC,Constant.EDIT);
                /*ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity)mContext,
                        Pair.create(viewHolder.title, "title"),
                        Pair.create(viewHolder.content, "content"));*/
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity)mContext,
                        Pair.create((View) viewHolder.title, "title"),
                        Pair.create((View)viewHolder.content, "content"),
                        Pair.create((View) viewHolder.note, "background"));
                // start the new activity
                mContext.startActivity(intent,options.toBundle());
            }
        });

    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mNotes, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        mNoteDao.deleteByKey(mNotes.get(position).getId());
        myApp.cursor.requery();
        mNotes.remove(position);
        notifyItemRemoved(position);
        mNoteListener.onItemRemoved();
    }
}

class ViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder{

    public TextView title,content;
    public CardView note;
    public ViewHolder(View itemView) {
        super(itemView);

        title = (TextView) itemView.findViewById(R.id.subhead);
        content = (TextView) itemView.findViewById(R.id.body2);
        note = (CardView) itemView.findViewById(R.id.note);
    }

    @Override
    public void onItemSelected() {
        itemView.setBackgroundColor(Color.LTGRAY);
    }

    @Override
    public void onItemClear() {
        String color = (String) itemView.getTag(R.id.itemview_back);
        itemView.setBackgroundColor(Color.parseColor(color));
    }
}
