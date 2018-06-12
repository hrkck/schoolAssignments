fileref = open("matches_table.txt", "r")
lines = fileref.readlines()
fileref.close()

teams = {}
team_col_ids = {}
for (i,line) in enumerate(lines):
    if i==0: continue
    line = line.strip()
    values = line.split("\t")
    team_name = values[0]
    teams[team_name] = {'O': 0, 'B':0, 'G': 0, 'M': 0, 'A': 0, 'Y': 0, 'P': 0}
    team_col_ids[i] = team_name 

print(teams)
print(team_col_ids)

for (i,line) in enumerate(lines):
   if i==0: continue
   line = line.strip()
   values = line.split("\t")
   team_name = values[0]
   games = values[1:]
   for (j,game) in enumerate(games):
      if game.find("-") != -1:  # so this is a played game. let's process
         teams[team_name]["O"] += 1
         opp_name = team_col_ids[j+1]
         teams[opp_name]["O"] += 1
         goals = game.split("-")
         goal1 = int(goals[0])
         goal2 = int(goals[1])
         teams[team_name]["A"] += goal1
         teams[team_name]["Y"] += goal2
         teams[opp_name]["A"] += goal2
         teams[opp_name]["Y"] += goal1         
         if goal1 == goal2:
           teams[team_name]["B"] += 1
           teams[opp_name]["B"] += 1
           teams[team_name]["P"] += 1
           teams[opp_name]["P"] += 1
         elif goal1 > goal2:  
           teams[team_name]["G"] += 1
           teams[opp_name]["M"] += 1
           teams[team_name]["P"] += 3
         else:         
           teams[team_name]["M"] += 1
           teams[opp_name]["G"] += 1
           teams[opp_name]["P"] += 3
         
      
   
# print(teams)
   
print("{:20s} {:>3s} {:>3s} {:>3s} {:>3s} {:>3s} {:>3s} {:>4s} {:>3s}"
   .format("Team Name","O", "G","B","M","A","Y","GF","P"))   
print("-"*60)
team_tuples = list(teams.items())
team_tuples.sort(reverse=True, key=lambda t: t[1]["A"])
team_tuples.sort(reverse=True, key=lambda t: t[1]["A"] - t[1]["Y"])
team_tuples.sort(reverse=True, key=lambda t: t[1]["P"])
for (name,data)  in team_tuples:
  print("{:20s} {:3d} {:3d} {:3d} {:3d} {:3d} {:3d} {:4d} {:3d}"
          .format(name,data["O"], data["G"],data["B"],data["M"],
                  data["A"],data["Y"],data["A"]-data["Y"],data["P"]))
   
