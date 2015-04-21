
f = open('Pisateljice.csv')
pisateljiceCSV = f.readlines()



#for row in pisateljiceCSV:
#  print row

#pisateljice = [ime, priimek, id_pis]

# Moj plan:

# ************ Naredimo tabelo pisateljice

# header row zgleda tako: [ime, priimek, id pisateljce]
# vse podatke razen zadnjega dobis iz pisateljiceCSV (delimiter med polji ;)

# CSV format: [priimek, ime;portret;rojstvo-smrt;drzava;vloga1 + vloga2]
# v prvem stolpcu CSV sta ime in priimek locena z vejico
# substringas ime v pisateljice.ime in priimek v pisateljice.priimek
#pisateljice = [["ime", "priimek", "id_pis","letoroj", "letosmrt"]]
pisateljice = []
idid = 0
for row in pisateljiceCSV:
	idid += 1
	vrstica = row.split(";")
	#print vrstica
	ime = vrstica[0].split(", ")[0]
	priimek = vrstica[0].split(", ")[1]
	letoroj = vrstica[2].split("-")[0]
	letosmrt = vrstica[2].split("-")[1]
	pisateljice.append([ime, priimek, idid, letoroj,letosmrt])
	#print letoroj

f.close()
f = open("pisateljiceOUT.txt", "w+")
f.write("{\n")
for pisateljica in pisateljice:
	stringg = "{ime: \""+pisateljica[1] + "\", priimek: \"" + pisateljica[0] + "\", id_pis: " + str(pisateljica[2]) + ", letoroj: " + str(pisateljica[3]) + ", letosmrt: " + str(pisateljica[4])+"},\n"
	f.write(stringg)
f.write("}")
f.close()

# portret prepises, lahko boolean ker je v CSV y in n

# tretji stolpec CSV razdelis na letoroj in letosmrt

# vloge razdelis na dva stolpca, nekatere imajo samo eno vlogo, zato je druga null

# *********** stevilo recepcij na delo

# Naredi tabelo dela: [src-id, stevilka pisateljice, naslov, src-title, src-date]
# src-id Gremo po vrsticah in na novo dodamo razlicne, ce so pa ze dodani povecamo stevilo

f = open('Recepcije.csv')
referenceCSV = f.readlines()
f.close() ################################################################# mogoce brejka kodo
pisatelji = []
for vrstica in pisateljice:
	pisatelji.append(vrstica[1] + " " + vrstica[0])

razlicni = []

f1 = open('DelaOUT.txt', "w+")
f1.write("{\n")

printdelaa = []
printdelac = []

for vrstica in referenceCSV:
	delovrst = vrstica.split(";")
	imep = vrstica.split(";")[1]
	if imep[0] == "[":
		imep = imep[7:]
		if imep[0] == "~":
			continue
		if ", " not in imep:			
	#		print imep
			continue
		
		ime = imep.split(", ")[1] + " " + imep.split(", ")[0]
		imep = ime
	#ime = imep.split(" ")
	if imep not in pisatelji:
		#print imep
		#if imep not in razlicni:
		#	razlicni.append(imep)
		continue
	# poisci id_pis
	id_pis = -1
	for pis in pisateljice:
		#print pis
		if imep == pis[1] + " " + pis[0]:#pis[0].split(", ")[1] + " " + pis[0].split(", ")[0]:
			id_pis = pis[2]
			break
	if id_pis == -1:
		continue
	printdela = "{id_dela: \""+ str(delovrst[4]) + "\", naslov: \"" + delovrst[7] + "\", leto: "+ str(delovrst[2])+", id_pis: " + str(id_pis) + ", st_referenc: "  # + " },\n"
	if printdela not in printdelaa:
		printdelaa.append(printdela)
		printdelac.append(1)
	else:
		i = 0
		for printdel in printdelaa:
			if printdel == printdela:
				printdelac[i] += 1
			i += 1

i = 0
for printdela in printdelaa:
	f1.write(printdela + str(printdelac[i]) + " },\n")
	i += 1
f1.write("}")
f1.close()

# parjenje del z del
# format: id_dela - id_dela

f = open("referenceOUT.txt", "w+")
f.write("{\n")
for ref in referenceCSV:
	id1 = ref.split(";")[4]
	id2 = ref.split(";")[14]
	f.write("{ id_dela_src: \"" + id1 + "\", id_dela_tgt: \"" + id2 + "\"},\n")
f.write("}");
f.close()



















