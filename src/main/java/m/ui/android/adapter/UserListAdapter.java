package m.ui.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import m.domain.user.User;
import m.ui.android.R;

/**
 * wee on 11/30/13.
 */
public class UserListAdapter extends ArrayAdapter<User> {

    private final Context context;
    private List<User> userList;

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

        userName.setText(userList.get(position).getName());

        return rowView;
    }
}
