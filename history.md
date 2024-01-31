circuitItems.stream()
.filter(item -> "O".equals(item.getType())) // Filtrer les éléments de type "O"
.collect(Collectors.groupingBy(
item -> item.getPosition().getRow(), // Regrouper par numéro de ligne
Collectors.counting() // Compter le nombre d'éléments dans chaque groupe
));
