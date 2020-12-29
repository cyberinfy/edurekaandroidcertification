package com.tcs.assignmentfour.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import com.tcs.assignmentfour.R;
import com.tcs.assignmentfour.SplashScreenActivity;
import com.tcs.assignmentfour.datamodel.PictureDataModel;
import com.tcs.assignmentfour.interfaces.FragmentToMainActivity;

import java.util.List;
public class  PicturesViewAdapter extends RecyclerView.Adapter<PicturesViewAdapter.PicturesViewHolder> {
    List<PictureDataModel> pictures;
    FragmentToMainActivity context;
    int fragmentID,pic_width,pic_height;
    public PicturesViewAdapter(int fragmentID, FragmentToMainActivity context, int pic_width, int pic_height) {
        this.fragmentID = fragmentID;
        this.context = context;
        this.pictures = context.getPictures();
        this.pic_width = pic_width;
        this.pic_height = pic_height;
    }

    @NonNull
    @Override
    public PicturesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.picture_card, parent, false);
        return new PicturesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PicturesViewAdapter.PicturesViewHolder holder, int position) {
        String name = pictures.get(position).getName();
        String path = pictures.get(position).getPath();
        holder.pictureId.setText(name);
        Picasso.get().load(path).resize(pic_width,pic_height).into(holder.picture);
    }

    @Override
    public int getItemCount() {
        return pictures.size();
    }

    public class PicturesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView picture;
        TextView pictureId;
        public PicturesViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            picture = (ImageView) itemView.findViewById(R.id.imageViewPicture);
            pictureId = (TextView) itemView.findViewById(R.id.textViewPictureName);
        }

        @Override
        public void onClick(View v) {
            TextView pictureId = v.findViewById(R.id.textViewPictureName);
            String name = pictureId.getText().toString();
            for(PictureDataModel picture: pictures){
                if(picture.getName().equals(name)){
                    context.showMoreDetails(fragmentID,picture.getPath());
                }
            }
        }
    }
}
