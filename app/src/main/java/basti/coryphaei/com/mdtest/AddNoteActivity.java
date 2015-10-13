package basti.coryphaei.com.mdtest;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import java.util.Date;

import basti.coryphaei.com.mdtest.application.App;
import basti.coryphaei.com.mdtest.utils.Constant;
import basti.coryphaei.com.mdtest.utils.FontUtils;
import basti.coryphaei.com.mdtest.utils.NoteUtils;
import basti.coryphaei.com.mdtest.view.ColorDialog;
import greendao.utils.Note;
import greendao.utils.Seq;

/**
 * Created by Bowen on 2015/10/11.
 */
public class AddNoteActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TextInputLayout  mTitleLayout,mContentLayout;
    private EditText mTitleEdittext,mContentEdittext;
    private Note note = new Note();
    private App myApp;
    private int func;
    private RelativeLayout mBackground;
    private int colorIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_note);
        initView();
        getDataFromLastActivity();
        initDates();
    }

    private void initDates() {

        myApp = (App) getApplicationContext();

    }

    private void getDataFromLastActivity() {

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null){

            func = bundle.getInt(Constant.FUNC);

            switch (func){
                case Constant.EDIT:
                    //编辑
                    note = (Note) bundle.getSerializable("note");
                    mTitleEdittext.setText(note.getTitle());
                    mContentEdittext.setText(note.getContent());
                    colorIndex = note.getBackgroundColor();
                    mBackground.setBackgroundColor(Color.parseColor(myApp.Colors[colorIndex]));
                    break;
                case Constant.ADD:
                    //增加
                    colorIndex = NoteUtils.getRandomColorIndex(myApp.Colors.length);
                    Log.i("colorIndex",myApp.Colors[colorIndex]);
                    mBackground.setBackgroundColor(Color.parseColor(myApp.Colors[colorIndex]));
                    break;
                default:break;
            }
        }

    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        mToolbar.setTitle("新建便签");
        mToolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mTitleLayout = (TextInputLayout) findViewById(R.id.title);
        mContentLayout = (TextInputLayout) findViewById(R.id.content);
        mTitleEdittext = mTitleLayout.getEditText();
        mTitleEdittext.setTypeface(FontUtils.getBlackMiddle(this));
        mTitleLayout.setHint(getResources().getString(R.string.title));
        mContentEdittext = mContentLayout.getEditText();
        mContentEdittext.setTypeface(FontUtils.getBlack(this));
        mContentLayout.setHint(getResources().getString(R.string.content));

        mBackground = (RelativeLayout) findViewById(R.id.background);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_addnote, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
            case R.id.share:
                //分享
                break;
            case R.id.like:
                //喜爱
                break;
            case R.id.color:
                //选择颜色
                ColorDialog dialog = new ColorDialog();
                dialog.show(getFragmentManager(),"test");
            default:break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        switch (func){
            case Constant.EDIT:
                //编辑
                updateNote();
                break;
            case Constant.ADD:
                //增加
                if (mTitleEdittext.getText().toString().trim().length() == 0 && mContentEdittext.getText().toString().trim().length()==0){

                }
                else {
                    addNote();
                }
                break;
            default:break;
        }

    }

    private void updateNote() {

        note.setTitle(mTitleEdittext.getText().toString().trim());
        note.setContent(mContentEdittext.getText().toString().trim());
        note.setBackgroundColor(colorIndex);
        note.setCreateDate(new Date());
        myApp.getNoteDao().update(note);
        myApp.cursor.requery();
    }

    private void addNote() {

        // 插入操作，简单到只要你创建一个 Java 对象
        Note note = new Note(null,mTitleEdittext.getText().toString(),mContentEdittext.getText().toString(),colorIndex,false,new Date(),new Date(),"tag",1);
        myApp.getNoteDao().insert(note);
        myApp.cursor.requery();

        myApp.getOrderIdDao().update(new Seq((long) 1,""));
    }

}
