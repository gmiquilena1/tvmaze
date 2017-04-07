package com.xpectra.tvmaze.helpers;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;
import com.xpectra.tvmaze.R;

/**
 * Created by Deneb Chorny
 */
public class CustomMessage {
    public static final int TYPE_ERROR = 0;
    public static final int TYPE_INFORMATION = 1;
    public static final int TYPE_WARNING = 2;
    public static final int TYPE_PROBLEMS = 3;
    public static final int TYPE_WARNING_OK_CANCEL = 4;
    public static final int TYPE_NO_SUCH_USER = 5;
    public static final int TYPE_PROGRESSBAR = 6;
    public static final int TYPE_UNAUTHORIZED = 7;
    public static final int TYPE_SUCCESS = 8;
    public static final int TYPE_QUESTION = 9;


    public static MaterialDialog.Builder get(Context context, int type, int message) {
        MaterialDialog.Builder dialog = null;

        if (context == null) {
            return null;
        }

        switch (type) {
            case TYPE_ERROR:
                dialog = new MaterialDialog.Builder(context)
                        .title(R.string.error)
                        .iconRes(R.drawable.ic_error)
                        .maxIconSizeRes(R.dimen.dialog_max_icon_size)
                        .content(message)
                        .positiveText(R.string.accept);
                break;

            case TYPE_INFORMATION:
                dialog = new MaterialDialog.Builder(context)
                        .title(R.string.information)
                        .iconRes(R.drawable.ic_information)
                        .maxIconSizeRes(R.dimen.dialog_max_icon_size)
                        .content(message)
                        .positiveText(R.string.accept);
                break;

            case TYPE_WARNING:
                dialog = new MaterialDialog.Builder(context)
                        .title(R.string.warning)
                        .iconRes(R.drawable.ic_warning)
                        .maxIconSizeRes(R.dimen.dialog_max_icon_size)
                        .content(message)
                        .positiveText(R.string.accept);
                break;

            case TYPE_PROBLEMS:
                dialog = new MaterialDialog.Builder(context)
                        .title(R.string.problems)
                        .iconRes(R.drawable.ic_problems)
                        .maxIconSizeRes(R.dimen.dialog_max_icon_size)
                        .content(message)
                        .positiveText(R.string.accept);
                break;

            case TYPE_WARNING_OK_CANCEL:
                dialog = new MaterialDialog.Builder(context)
                        .title(R.string.warning)
                        .iconRes(R.drawable.ic_warning)
                        .maxIconSizeRes(R.dimen.dialog_max_icon_size)
                        .content(message)
                        .positiveText(R.string.accept)
                        .negativeText(R.string.cancel);
                break;

            case TYPE_NO_SUCH_USER:
                dialog = new MaterialDialog.Builder(context)
                        .title(R.string.error)
                        .iconRes(R.drawable.ic_no_such_user)
                        .maxIconSizeRes(R.dimen.dialog_max_icon_size)
                        .content(message)
                        .positiveText(R.string.accept);
                break;

            case TYPE_PROGRESSBAR:
                dialog = new MaterialDialog.Builder(context)
                        .title(R.string.please_wait)
                        .content(message)
                        .cancelable(false)
                        .autoDismiss(false)
                        .progress(true, 0);
                break;

            case TYPE_UNAUTHORIZED:
                dialog = new MaterialDialog.Builder(context)
                        .title(R.string.unauthorized)
                        .iconRes(R.drawable.ic_prohibition)
                        .maxIconSizeRes(R.dimen.dialog_max_icon_size)
                        .content(message)
                        .positiveText(R.string.accept);
                break;

            case TYPE_SUCCESS:
                dialog = new MaterialDialog.Builder(context)
                        .title(R.string.success)
                        .iconRes(R.drawable.ic_success)
                        .maxIconSizeRes(R.dimen.dialog_max_icon_size)
                        .content(message)
                        .positiveText(R.string.accept);
                break;
            case TYPE_QUESTION:
                dialog = new MaterialDialog.Builder(context)
                        .title(R.string.question)
                        .iconRes(R.drawable.ic_question)
                        .maxIconSizeRes(R.dimen.dialog_max_icon_size)
                        .content(message)
                        .positiveText(R.string.accept)
                        .negativeText(R.string.cancel);
                break;
        }

        return dialog;
    }


    public static MaterialDialog.Builder get(Context context, int type, String message) {
        MaterialDialog.Builder dialog = null;

        if (context == null) {
            return null;
        }

        switch (type) {
            case TYPE_ERROR:
                dialog = new MaterialDialog.Builder(context)
                        .title(R.string.error)
                        .iconRes(R.drawable.ic_error)
                        .maxIconSizeRes(R.dimen.dialog_max_icon_size)
                        .content(message)
                        .positiveText(R.string.accept);
                break;

            case TYPE_INFORMATION:
                dialog = new MaterialDialog.Builder(context)
                        .title(R.string.information)
                        .iconRes(R.drawable.ic_information)
                        .maxIconSizeRes(R.dimen.dialog_max_icon_size)
                        .content(message)
                        .positiveText(R.string.accept);
                break;

            case TYPE_WARNING:
                dialog = new MaterialDialog.Builder(context)
                        .title(R.string.warning)
                        .iconRes(R.drawable.ic_warning)
                        .maxIconSizeRes(R.dimen.dialog_max_icon_size)
                        .content(message)
                        .positiveText(R.string.accept);
                break;

            case TYPE_PROBLEMS:
                dialog = new MaterialDialog.Builder(context)
                        .title(R.string.problems)
                        .iconRes(R.drawable.ic_problems)
                        .maxIconSizeRes(R.dimen.dialog_max_icon_size)
                        .content(message)
                        .positiveText(R.string.accept);
                break;

            case TYPE_WARNING_OK_CANCEL:
                dialog = new MaterialDialog.Builder(context)
                        .title(R.string.warning)
                        .iconRes(R.drawable.ic_warning)
                        .maxIconSizeRes(R.dimen.dialog_max_icon_size)
                        .content(message)
                        .positiveText(R.string.accept)
                        .negativeText(R.string.cancel);
                break;

            case TYPE_NO_SUCH_USER:
                dialog = new MaterialDialog.Builder(context)
                        .title(R.string.error)
                        .iconRes(R.drawable.ic_no_such_user)
                        .maxIconSizeRes(R.dimen.dialog_max_icon_size)
                        .content(message)
                        .positiveText(R.string.accept);
                break;

            case TYPE_PROGRESSBAR:
                dialog = new MaterialDialog.Builder(context)
                        .title(R.string.please_wait)
                        .content(message)
                        .cancelable(false)
                        .autoDismiss(false)
                        .progress(true, 0);
                break;

            case TYPE_UNAUTHORIZED:
                dialog = new MaterialDialog.Builder(context)
                        .title(R.string.unauthorized)
                        .iconRes(R.drawable.ic_prohibition)
                        .maxIconSizeRes(R.dimen.dialog_max_icon_size)
                        .content(message)
                        .positiveText(R.string.accept);
                break;
            case TYPE_QUESTION:
                dialog = new MaterialDialog.Builder(context)
                        .title(R.string.question)
                        .iconRes(R.drawable.ic_question)
                        .maxIconSizeRes(R.dimen.dialog_max_icon_size)
                        .content(message)
                        .positiveText(R.string.accept)
                        .negativeText(R.string.cancel);
                break;

        }


        return dialog;
    }

}
