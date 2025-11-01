class FormatFactory {
    public static TextFormatStrategy createFormat(String type) {
        switch (type.toLowerCase()) {
            case "bold":
                return new BoldFormatStrategy();
            case "italic":
                return new ItalicFormatStrategy();
            case "underline":
                return new UnderlineFormatStrategy();
            case "bolditalic":
                return new BoldItalicDecorator(new BoldFormatStrategy());
            case "boldunderline":
                return new BoldUnderlineDecorator(new BoldFormatStrategy());
            default:
                throw new IllegalArgumentException("Unknown format: " + type);
        }
    }
}