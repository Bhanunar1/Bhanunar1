#include <iostream>
#include <cstring>
using namespace std;
int main() {

	char name1[10] ="C++";
	char name2[10] ="observation";
	char name3[10];

	int  len;
	strcpy(name3, name1);
	cout << "strcpy( name3, name1) : " << name3 << endl;

	strcat(name1, name2);
	cout << "strcat( name1, name2): " << name1 << endl;

	len = strlen(name1);
	cout << "strlen(name1) : " << len << endl;
	return 0;
}

