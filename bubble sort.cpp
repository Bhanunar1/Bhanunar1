#include<iostream>
using namespace std;
template<class S>
void  b_sort(S u[],int k)
{
	for(int x=0;x<k-1;x++)
	for(int y=0;x<y;y--)
	if(u[y]<u[y-1])
	{
		S p;
		p=u[y];
		u[y]=u[y-1];
		u[y-1]=p;
	}
}
int main()
{
	int i[5]={1,2,3,4,5};
	float f[5]={2.4,5.6,3.4,4.4,0.4};
	b_sort(i,5);
	b_sort(f,5);
	int x=0,y=0;
	cout<<"\n ascending order of integer:"<<endl;
	while(x<5)
	cout<<i[x++]<<" ";
	cout<<"\n ascending order of float:"<<endl;
	while(y<5)
	cout<<f[y++]<<" ";
	return 0;
	
}
