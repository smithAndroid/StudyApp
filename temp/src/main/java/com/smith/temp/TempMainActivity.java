package com.smith.temp;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.smith.temp.base.BaseActivity;
import com.smith.temp.base.BaseApplication;
import com.smith.temp.dagger.DaggerPersonComponent;
import com.smith.temp.dagger.Food;
import com.smith.temp.dagger.Person;
import com.smith.temp.dagger.PersonModule;
import com.smith.temp.greendao.Student;
import com.smith.temp.greendao.db.DaoSession;


import org.greenrobot.greendao.async.AsyncOperation;
import org.greenrobot.greendao.async.AsyncOperationListener;
import org.greenrobot.greendao.async.AsyncSession;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;

public class TempMainActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.temp_text)
    TextView tempText;

    @Named("a")
    @Inject
    Person person;

    @Named("a")
    @Inject
    Person person1;

    @Named("b")
    @Inject
    Person personB;

    @Named("b")
    @Inject
    Person personB1;

    @Named("c")
    @Inject
    Person personC;

    @Inject
    Food food;

    private DaoSession daoSession;

    @Override
    protected int getContentViewId() {
        return R.layout.temp_activity_main;
    }

    StringBuilder sb = new StringBuilder();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerPersonComponent.builder().personModule(new PersonModule()).build().inject(this);
        sb.append(person.toString()).append("\n").append(person1.toString()).append("\n").append(personB.toString()).append("\n").append(personB1.toString()).append("\n");
        sb.append(food.getMeat()).append("\n")
                .append(personC.toString()).append("\n").append(personC.getName()).append(" drink ").append(personC.getFood().getDrink()).append("\n");
        tempText.setText(sb.toString());

        tempText.setOnClickListener(this);
        insertDb();
    }

    private void insertDb() {
        Student student = new Student();
        student.setId(1L);
        student.setStudentNo(123456);
        student.setName("JACK");
        student.setAge(24);
        student.setAddress("深圳");
        daoSession = ((BaseApplication)getApplication()).getDaoSession();
        daoSession.insertOrReplace(student);
        AsyncSession asyncSession = daoSession.startAsyncSession();
        asyncSession.setListener(operation -> {

        });
        asyncSession.insert(student);
    }


    @Override
    public void onClick(View v) {
        String tmp = "world! cool";
        resetDagger();
    }

    private void resetDagger() {
        DaggerPersonComponent.create().inject(this);
//        sb.append(person.toString()).append("\n").append(person1.toString()).append("\n").append(personB.toString()).append("\n").append(personB1.toString()).append("\n");
//        tempText.setText(sb.toString());
        Student s = daoSession.load(Student.class,1L);
        StringBuilder sb = new StringBuilder();
        sb.append(s.getId()).append(s.getStudentNo()).append(s.getName()).append(s.getAge()).append(s.getAddress());
        sb.append("world");
        sb.append("hello1");
        tempText.setText(sb.toString());
    }
}
