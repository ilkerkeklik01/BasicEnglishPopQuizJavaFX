class Question {
    private String questionText;
    private boolean correctAnswer;

    public Question(String questionText, boolean correctAnswer) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public boolean isCorrectAnswer() {
        return correctAnswer;
    }
}