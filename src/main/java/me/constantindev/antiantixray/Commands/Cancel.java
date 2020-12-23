package me.constantindev.antiantixray.Commands;

import me.constantindev.antiantixray.AntiAntiXray;

public class Cancel extends Base {
    public Cancel() {
        super("Cancel", new String[]{"c", "cancel", "abort"}, "Aborts all current jobs");
    }

    @Override
    public void run(String[] args) {
        AntiAntiXray.jobs.forEach(refreshingJob -> {
            refreshingJob.cancel();
        });
        super.run(args);
    }
}
