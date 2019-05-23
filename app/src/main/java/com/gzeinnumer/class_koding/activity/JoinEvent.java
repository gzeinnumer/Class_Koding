package com.gzeinnumer.class_koding.activity;
//done
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gzeinnumer.class_koding.R;
import com.gzeinnumer.class_koding.helper.MyFunction;
import com.gzeinnumer.class_koding.helper.SessionManager;
import com.gzeinnumer.class_koding.presenter.MainInterface;
import com.gzeinnumer.class_koding.presenter.MainPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JoinEvent extends MyFunction {

    public static final String DATA = "data";
    private SessionManager sessionManager;

    MainInterface.I_JoinEvent i_joinEvent;
    @BindView(R.id.user_name_join)
    TextView userNameJoin;
    @BindView(R.id.email_join)
    TextView emailJoin;
    @BindView(R.id.btn_join_now)
    Button btnJoinNow;
    String event_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_event);
        ButterKnife.bind(this);
        i_joinEvent = new MainPresenter(context);
        sessionManager = new SessionManager(context);
        event_id = getIntent().getStringExtra(DATA);

        userNameJoin.setText(sessionManager.getName());
        emailJoin.setText(sessionManager.getEmail());

        btnJoinNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i_joinEvent.regisToTableJoinEvent(sessionManager.getUserId(), event_id);
            }
        });
    }
}
