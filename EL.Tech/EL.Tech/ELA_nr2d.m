clear all; clc; format compact; format short g;
%parameter
%stimmt nicht, vermutlich c1 kaputt, impedanz von c1->unendlich
R=820;
C1=2.2e-6;
C2=47e-9;
dt=1e-1;
f_0=10;
f_E=1e6;
N=1e6;
lw=3;
%Funktionen
g=@(f)(C1/(C1+C2)).*(1i*2*pi*f*R*(C1+C2))./(1+1i*2*pi*f*R*(C1+C2));
% daten
f_data=linspace(f_0,f_E,N);
g_data=g(f_data);
%exp data
f_exp_Data=[10,30,100,300,1000,3000,10000,30000,100000,300000];
second_u_Data_CL=[0.05,0.12,0.15,0.16,0.15,0.1,0.04,0.01,0.003,0.001];
second_phi_Data_CL=[-7/18,-2/9,-1/12,-1/30,0,0,0,0,0,0];
%PLOT
figure(4);
subplot(2,1,1);
semilogx(f_data,20*log(abs(g_data))/log(10),'linewidth',lw);
hold on;
semilogx(f_exp_Data,20*log(second_u_Data_CL*2)/log(10),'linewidth',lw);
hold off;
xlabel('f[Hz]');ylabel('|G|[dB]');title('Amplitudengang |G(f)|')
subplot(2,1,2);
semilogx(f_data,angle(g_data)/pi,'linewidth',lw);
hold on;
semilogx(f_exp_Data,-1*second_phi_Data_CL,'linewidth',lw);
hold off;
xlabel('f[Hz]');
ylabel('\phi[\pi]');
title('Phasengang in rad [\pi]');
grid on;