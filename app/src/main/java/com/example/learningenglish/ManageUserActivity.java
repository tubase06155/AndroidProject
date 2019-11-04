package com.example.learningenglish;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.learningenglish.Entity.User;
import com.example.learningenglish.adapter.UserAdapter;
import com.example.learningenglish.dal.UserDAO;

import java.util.List;

public class ManageUserActivity extends AppCompatActivity {
ListView listViewManageUser;
UserAdapter userAdapter;
List<User> listUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_user);
        listViewManageUser = findViewById( R.id.listViewManageUser);
        UserDAO userDAO = new UserDAO();
        try {
            listUser = userDAO.getListOfUser();
            userAdapter = new UserAdapter(this, R.layout.manage_user_layout, listUser);
            listViewManageUser.setAdapter(userAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        registerForContextMenu(listViewManageUser);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add("Reset password");
        menu.add("Edit user");

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getTitle().equals("Reset password")){
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            int position = info.position;
            User user = (User) listViewManageUser.getAdapter().getItem(position);
            //  Customer customer = (Customer) customerList.get(listView.getSelectedItemPosition());
            //Customer customer = customerList.get(selectedItem);
            UserDAO userDAO = new UserDAO();
            try {
                userDAO.resetPassword(user.getUserID() + "");
            } catch (Exception e) {
                e.printStackTrace();
            }
            Toast.makeText(getApplicationContext(),"Reset password for " + user.getUsername() + " successfully",Toast.LENGTH_SHORT).show();

        }
        else if (item.getTitle().equals("Edit user")){
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            int position = info.position;
            User user = (User) listViewManageUser.getAdapter().getItem(position);

            Intent intent = new Intent(this,ManageUserEditingActivity.class);
            intent.putExtra("user",user);
            startActivityForResult(intent,101);
        }

        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 101 && resultCode == 201) {
            UserDAO userDAO = new UserDAO();
            try {
                listUser = userDAO.getListOfUser();
                userAdapter = new UserAdapter(this, R.layout.manage_user_layout, listUser);
                listViewManageUser.setAdapter(userAdapter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
