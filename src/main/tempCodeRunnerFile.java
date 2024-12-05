 public static String[] getText(){
        String[] wordList = new String[140];
        try{
            File puzzle = new File("CeresSearch/src/res/puzzle.txt");
            Scanner sc = new Scanner(puzzle);
            for (int i = 0; i < wordList.length; i++)
            while (sc.hasNextLine()) {
                wordList[i] = sc.nextLine();
            }
            sc.close();
            return wordList;
        } catch (FileNotFoundException e){
            System.out.println("File not found");
            e.printStackTrace();
            return wordList;
        }


    }