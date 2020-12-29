package com.tcs.assignmentthree;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FirstFragmentAdapter extends RecyclerView.Adapter<FirstFragmentAdapter.ColorViewHolder> {
    private ColorDataModel[] colors;
    private FirstFragmentToAfterLoginActivity mainContext;

    public FirstFragmentAdapter(ColorDataModel[] colors, FirstFragmentToAfterLoginActivity mainContext) {
        this.colors = colors;
        this.mainContext = mainContext;
    }

    @NonNull
    @Override
    public ColorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.fragment_first_item, parent, false);
        return new ColorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ColorViewHolder holder, int position) {
        String colorName = colors[position].getName();
        int hexcode = colors[position].getHexcode();
        holder.colorName.setText(colorName);
        holder.colorIcon.setBackgroundResource(hexcode);
    }

    @Override
    public int getItemCount() {
        return colors.length;
    }

    public class ColorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView colorIcon;
        TextView colorName;
        public ColorViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            colorIcon = (ImageView) itemView.findViewById(R.id.colorIcon);
            colorName = (TextView) itemView.findViewById(R.id.colorName);
        }

        @Override
        public void onClick(View v) {
            TextView colorName = v.findViewById(R.id.colorName);
            String color = colorName.getText().toString();
            for(ColorDataModel c: colors){
                if(c.name.equals(color)){
                    mainContext.paint(c.hexcode,c.name);
                }
            }
        }
    }
}
