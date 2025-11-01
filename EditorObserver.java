class EditorObserver implements Observer {
    private String name;

    public EditorObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println("[Observer " + name + "] " + message);
    }
}