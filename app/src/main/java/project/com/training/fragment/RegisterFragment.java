package project.com.training.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import project.com.training.R;
import project.com.training.dao.UserDao;
import project.com.training.model.User;


public class RegisterFragment extends Fragment {



    @BindView(R.id.ed_re0)
    EditText edRe0;

    @BindView(R.id.ed_re1)
    EditText edRe1;

    @BindView(R.id.ed_re2)
    EditText edRe2;

    @BindView(R.id.ed_re3)
    EditText edRe3;
    @BindView(R.id.confirm)
    Button confirm;
    Unbinder unbinder;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.confirm)
    public void onClick() {
        String email=edRe0.getText().toString();
        String name=edRe1.getText().toString();
        String psd=edRe2.getText().toString();
        String conpsd=edRe3.getText().toString();
        User user=new User();
        user.setEmail(email);
        user.setPasswd(psd);
        user.setUsername(name);
        UserDao userDao=new UserDao(getActivity());

        if(!psd.equals(conpsd)){
            Toast.makeText(getActivity(),"密码输入不一致！",Toast.LENGTH_SHORT).show();

        }else{
            if(userDao.ifUserExist(email)){
                Toast.makeText(getActivity(),"该邮箱已经被注册了！",Toast.LENGTH_SHORT).show();

            }else{

                if(!userDao.insert(user)){
                    Toast.makeText(getActivity(),"注册失败！",Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(getActivity(),"注册成功！",Toast.LENGTH_SHORT).show();

                }

            }
        }

    }
}
