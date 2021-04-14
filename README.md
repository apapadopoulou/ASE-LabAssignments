# ASE-LabAssignments
A repository to deliver the lab assignments of the Applied Software Engineering course.

## <a name="table-of-contents"></a>Table of contents
* [ase2021_practical_assignments](#parent)
* [gradeshistogram](#grades-histogram)

---

## <a name="parent"></a>ase2021_practical_assignments

This maven project is the parent project and was created for the purposes of the second lab assignment.

---

## <a name="grades-histogram"></a>gradeshistogram

This maven project is the module project and has [ase2021_practical_assignments](#parent) as a parent project.
It contains a class that takes a grades file as input and prooduces a histogram with the frequencies of the grades.
### How to pruduce the histogram

**Step 1:** Clone the repository with `git clone https://github.com/apapadopoulou/ASE-LabAssignments.git`.

**Step 2:** Switch to the `gradehistogram` directory and run `mvn package`.

That's it! Now the project is built and you can run the .jar file passing a grades file as a command line argument. The grade histogram will appear in your screen.