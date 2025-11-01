class EditorUIObserver implements Observer {
    @Override
    public void update(String message) {
        System.out.println("[UI] " + message);
    }
}
