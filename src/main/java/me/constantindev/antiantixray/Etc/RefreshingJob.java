package me.constantindev.antiantixray.Etc;

import me.constantindev.antiantixray.GUI.ProgressBar;

public class RefreshingJob {
    public ProgressBar progress;
    public Runner refresher;
    public Thread runner;
    public RefreshingJob(Runner refresher, ProgressBar pbar) {
        this.refresher = refresher;
        this.progress = pbar;
        this.runner = new Thread(refresher);
        this.runner.start();

    }
    public void cancel() {
        refresher.isRunning = false;
        this.progress.done = true;
    }
}
