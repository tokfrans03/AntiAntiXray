package me.constantindev.antiantixray.Commands;

import me.constantindev.antiantixray.AntiAntiXray;
import me.constantindev.antiantixray.Etc.RefreshingJob;

public class Cancel extends Base {
    public Cancel() {
        super("Cancel", new String[]{"c", "cancel", "abort", "s", "stop"}, "Aborts all current jobs");
    }

    @Override
    public void run(String[] args) {
        AntiAntiXray.jobs.forEach(RefreshingJob::cancel);
        super.run(args);
    }
}
