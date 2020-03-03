package by.it.kuzmichalex.calc_jd02_05;

class Parser {

   Var calc(String expression) throws CalcException {
       expression = expression.replace("\\s+", "");
       expression = expression.replace(" ", "");
       ReversePolishNotation rpn = new ReversePolishNotation();
       String[] partsOfExpression = expression.split("=");
       if (partsOfExpression.length == 1) {
           rpn.parseExpression(expression);
           return rpn.calc();
       } else if (partsOfExpression.length == 2) {
           rpn.parseExpression(partsOfExpression[1]);
           Var rightVar=rpn.calc();
           Var.save(partsOfExpression[0],rightVar);
           return rightVar;
       } else throw new CalcException(LanguageManager.get(Messages.error_in_expression));
   }



}
