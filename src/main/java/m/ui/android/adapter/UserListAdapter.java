package m.ui.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import m.domain.user.User;
import m.infrastructure.SampleData;
import m.ui.android.R;

/**
 * wee on 11/30/13.
 */
public class UserListAdapter extends ArrayAdapter<User> {

    private final Context context;
    private List<User> userList;


    private User currentUser = SampleData.currentUser;

    public UserListAdapter(Context context, int resource, List<User> userList) {

        super(context, resource, userList);
        this.context = context;
        this.userList = userList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.search_user_item, parent, false);
        TextView userName = (TextView) rowView.findViewById(R.id.user_name);
        Button followButton = (Button) rowView.findViewById(R.id.follow_button);

        if (userList.size() >= position + 1) {
            User selectedUser = userList.get(position);

            if (selectedUser.isMyFollower(currentUser)) {
                followButton.setText(R.string.unfollow);
            }
        }

        userName.setText(userList.get(position).getName());

        return rowView;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
