class AutoSaveObserver implements Observer {
    @Override
    public void update(String message) {
        if (message.contains("edited") || message.contains("saved")) {
            System.out.println("[AutoSave] Backup created. Event: " + message);
        }
    }
}