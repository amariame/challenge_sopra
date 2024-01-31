*new strategy*

```
circuitItems.stream()
.filter(item -> "O".equals(item.getType())) // Filtrer les éléments de type "O"
.collect(Collectors.groupingBy(
item -> item.getPosition().getRow(), // Regrouper par numéro de ligne
Collectors.counting() // Compter le nombre d'éléments dans chaque groupe
));
```


**How strategy**

```
//List<Position> taches =items.stream().filter(item -> item.getType().equals("O")).map(Item::getPosition).collect(Collectors.toList());
List<Position> taches =items.stream().map(Item::getPosition).collect(Collectors.toList());

        //move(position)
        Position p1 =new Position();
        Position p2 =new Position();
        int lane = p.getLane();
        int row = p.getRow();

        p1.setLane(lane);
        p1.setRow(row+1);
        p2.setLane(lane);
        p2.setRow(row+2);

        if(board.getSelfPlayer().getInventory()!=null){

            System.out.println("Bonus !!!!!!");
            if(!board.getSelfPlayer().getInventory().equals("R"))
                move = "USE_BONUS";
        }
        if(taches.contains(p2) || taches.contains(p1)){
            if(p.getLane() == 0){
                p1.setLane(p1.getLane()+1);
                move = "RIGHT";
            } else if (p.getLane() ==4) {
                p1.setLane(p1.getLane()-1);
                move = "LEFT";
            }
            else {
                p1.setLane(p1.getLane()-1);
                if(taches.contains(p1)){
                    move= "RIGHT";
                }
                else {
                    move= "LEFT";
                }
            }
        }
        /*else if(taches.contains(p1)){

            if(p.getLane() == 0){
                p1.setLane(p1.getLane()+1);
                move = "RIGHT";
            } else if (p.getLane() ==4) {
                p1.setLane(p1.getLane()-1);
                move = "LEFT";
            }
            else {
                p1.setLane(p1.getLane()-1);
                if(taches.contains(p1)){
                    move= "LEFT";
                }
                else {
                    move= "RIGHT";
                }
            }
        }*/
```

Implementer cette nouvelle strategie
