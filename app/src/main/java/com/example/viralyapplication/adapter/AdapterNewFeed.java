package com.example.viralyapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viralyapplication.R;
import com.example.viralyapplication.repository.model.FooterModel;

import java.util.List;

public class AdapterNewFeed extends RecyclerView.Adapter {

    List<RecyclerViewItem> mRecyclerViewItems;
    private static final int HEADER_ITEM = 0;
    private static final int FOOTER_ITEM = 1;
    private static final int FOOD_ITEM = 2;
    Context mContext;


    public void Adapter(List<RecyclerViewItem> mRecyclerViewItems, Context mContext) {
        this.mRecyclerViewItems = mRecyclerViewItems;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        View mRow;
        if (viewType == HEADER_ITEM) {
            mRow = mInflater.inflate(R.layout.include_header_new_feed_layout, parent, false);
            return new HeaderHolder(mRow);
        } else if (viewType == FOOTER_ITEM) {
            mRow = mInflater.inflate(R.layout.include_row_newfeed_layout, parent, false);
            return new FooterHolder(mRow);
        } else if (viewType == FOOD_ITEM) {
            mRow = mInflater.inflate(R.layout.include_footer_content_layout, parent, false);
            return new FoodItemHolder(mRow);
        }
        return null;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RecyclerViewItem mRecyclerViewItem = mRecyclerViewItems.get(position);
//        if (holder instanceof HeaderHolder) {
//            HeaderHolder mHeaderHolder = (HeaderHolder) holder;
//            HeaderModel mHeader = (HeaderModel) mRecyclerViewItem;
//            mHeaderHolder.tvNameUser.setText(mHeader.getHeaderText());
//            Glide.with(mContext).load(mHeader.getImageUrl()).into(mHeaderHolder.ivAvatarUser);
//        } else if (holder instanceof FooterHolder) {
//            FooterHolder mFooterHolder = (FooterHolder) holder;
//            FooterModel mFooter = (FooterModel) mRecyclerViewItem;
//
//            mFooterHolder.tvGmailApp.setText(R.string.support_viraly_com_text);
//            mFooterHolder.tvNameApp.setText(R.string.infor_content_text);
//            Glide.with(mContext).load(mFooter.getImage()).placeholder(R.drawable.viraly_145x197).into(mFooterHolder.ivLogoApp);
//        } else if (holder instanceof FoodItemHolder) {
//            FoodItemHolder mFoodItemHolder = (FoodItemHolder) holder;
//            FoodItem mFood = (FoodItem) mRecyclerViewItem;
//            mFoodItemHolder.mTextViewFoodTitle.setText(mFood.getTitle());
//            mFoodItemHolder.mTextViewDescription.setText(mFood.getDescription());
//            mFoodItemHolder.mTextViewPrice.setText(mFood.getPrice());
//            Glide.with(mContext).load(mFood.getImageUrl()).into(mFoodItemHolder.mImageViewFood);
//
//            if (mFood.isHot()) {
//                mFoodItemHolder.mTextViewIsHot.setVisibility(View.VISIBLE);
//            } else {
//                mFoodItemHolder.mTextViewIsHot.setVisibility(View.GONE);
//            }
//
//        }

    }

    @Override
    public int getItemViewType(int position) {
        RecyclerViewItem mRecyclerViewItem = mRecyclerViewItems.get(position);
//        if (mRecyclerViewItem instanceof HeaderModel) {
//            return HEADER_ITEM;
//        } else if (mRecyclerViewItem instanceof FooterModel) {
//            return FOOTER_ITEM;
//        } else if (mRecyclerViewItem instanceof FoodItem) {
//            return FOOD_ITEM;
//        } else
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return mRecyclerViewItems.size();
    }

    private class FoodItemHolder extends RecyclerView.ViewHolder {
        TextView tvNameUser, tvTimePost, tvContentPost, tvTitleLikeNumber,
                tvTitleCommentNumber, tvLikeAction, tvCommentAction;
        ImageView ivOptional, ivAvatar, ivUserPosted;


        public FoodItemHolder(@NonNull View itemView) {
            super(itemView);
            tvNameUser = itemView.findViewById(R.id.tv_name_user_new_feed);
            tvTimePost = itemView.findViewById(R.id.tv_time_posted);
            tvContentPost = itemView.findViewById(R.id.tv_content_post);
//            tvTitleLikeNumber = itemView.findViewById(R.id.tv_title_number_like);
//            tvTitleCommentNumber = itemView.findViewById(R.id.tv_title_number_comment);
//            tvLikeAction = itemView.findViewById(R.id.tv_like_action);
//            tvCommentAction = itemView.findViewById(R.id.tv_comment_action);
            ivOptional = itemView.findViewById(R.id.iv_optional);
            ivAvatar = itemView.findViewById(R.id.iv_avatar_user);
            ivUserPosted = itemView.findViewById(R.id.iv_image_user_posted);

        }
    }

    private class HeaderHolder extends RecyclerView.ViewHolder {
        TextView tvNameUser, tvCancel;
        ImageView ivAvatarUser, ivCamera;
        EditText edtContentPost;
        Button btnPostContent;


        public HeaderHolder(@NonNull View itemView) {
            super(itemView);
            tvNameUser = itemView.findViewById(R.id.tv_name_user_new_feed);
            ivCamera = itemView.findViewById(R.id.iv_func_camera);
            tvCancel = itemView.findViewById(R.id.tv_cancel);
            ivAvatarUser = itemView.findViewById(R.id.iv_avatar_user);
            edtContentPost = itemView.findViewById(R.id.edt_status_user);
            btnPostContent = itemView.findViewById(R.id.button_create_post);
        }
    }

    private class FooterHolder extends RecyclerView.ViewHolder {
        TextView tvNameApp, tvGmailApp;
        ImageView ivLogoApp;

        public FooterHolder(@NonNull View itemView) {
            super(itemView);
            tvNameApp = itemView.findViewById(R.id.tv_name_app);
            tvGmailApp = itemView.findViewById(R.id.tv_gmail_app);
            ivLogoApp = itemView.findViewById(R.id.iv_logo_app);
        }
    }


}

