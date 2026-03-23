public boolean checkClick(String clickedId) {

        if (ids.contains(clickedId)) {
            setCorrect();
            return true;
        } else {
            setWrong();
            return false;
        }
    }