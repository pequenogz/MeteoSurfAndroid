package com.meteosurf.meteosurf.fragments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.meteosurf.meteosurf.R;
import com.meteosurf.meteosurf.api.Comment;

import java.util.List;

class CommentsAdapter extends ArrayAdapter<Comment> {

    private Context context;
    private int resource;
    private List<Comment> comments;

    TextView tvTitle;
    EditText etComment;

    public CommentsAdapter(@NonNull Context context, int resource, @NonNull List<Comment> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.comments = objects;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(resource, parent, false);

        // Comment item
        Comment comment = comments.get(position);

        // Elements of item
        tvTitle = view.findViewById(R.id.tvTitle);
        etComment = view.findViewById(R.id.etComment);

        tvTitle.setText(comment.getDate().concat(" por ").concat(comment.getUser()).concat(":"));
        etComment.setText(comment.getComment());
        etComment.setKeyListener(null);

        return view;

    }
}
