package com.erge.mylibrary.utils;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Vibrator;

import java.io.IOException;

/**
 * 播放音频、视频，控制震动相关工具类
 */
public class PlayUtils {

	/**
	 * 播放存放在本地的音频文件
	 * 
	 * @param path
	 *            音频存放路径
	 */
	public static void playAudio(String path) throws IOException{
		final MediaPlayer player = new MediaPlayer();
		player.setDataSource(path);
		player.prepare();
		player.start();
		player.setOnCompletionListener(new OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer mp) {
				mp.release();
			}
		});
	}

	/**
	 * 播放资源音频文件
	 * 
	 * @param context
	 * @param resid
	 */
	public static void playAudio(Context context, int resid) {
		final MediaPlayer player = MediaPlayer.create(context, resid);
		if (player != null) {
			player.start();
			player.setOnCompletionListener(new OnCompletionListener() {
				@Override
				public void onCompletion(MediaPlayer mp) {
					mp.release();
				}
			});
		}
	}

	/**
	 * 调用系统自带播放器 播放视频
	 * 
	 * @param context
	 *            上下文环境
	 * @param path
	 *            视频存放路径
	 */
	public static void playVideo(Context context, String path) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		String videoPath = "file:///" + path;
		intent.setDataAndType(Uri.parse(videoPath), "video/*");
		context.startActivity(intent);
	}

	/**
	 * 震动
	 * 
	 * @param milliseconds
	 *            震动时长
	 */
	public static void normalVibrate(Context context, long milliseconds) {
		Vibrator vibrator = (Vibrator) context.getSystemService(Service.VIBRATOR_SERVICE);
		vibrator.vibrate(milliseconds);
	}

	/**
	 * 节奏性震动
	 * 
	 * @param pattern
	 *            震动时长的数组
	 */
	public static void rhythmicalVibrate(Context context, long[] pattern) {
		Vibrator vibrator = (Vibrator) context.getSystemService(Service.VIBRATOR_SERVICE);
		vibrator.vibrate(pattern, -1);
	}

	/**
	 * 重复震动
	 * 
	 * @param pattern
	 *            震动时长的数组
	 */
	public static void repeatVibrate(Context context, long[] pattern) {
		Vibrator vibrator = (Vibrator) context.getSystemService(Service.VIBRATOR_SERVICE);
		vibrator.vibrate(pattern, 0);
	}

}
