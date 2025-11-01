class LoggerObserver implements Observer {
    @Override
    public void update(String message) {
        System.out.println("[Logger] Log entry: " + message);
    }
}