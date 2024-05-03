import re

class RequestDataValidator:
    
    def is_valid_cpf(cpf):
        cpf = ''.join(re.findall(r'\d', cpf))   # Limpa a string para manter apenas dígitos
        if len(cpf) != 11 or len(set(cpf)) == 1:
            return False

        for i in range(9, 11):
            value = sum((int(cpf[num]) * ((i+1) - num) for num in range(0, i)))
            digit = ((value * 10) % 11) % 10
            if digit != int(cpf[i]):
                return False
        return True
    
    def is_valid_email(email):
        # Regex para validar email
        pattern = re.compile(r'^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$')
        if pattern.match(email):
            return True
        else:
            return False
        
    def is_valid_phone_number(phone_number):
        # Regex para validar número de celular brasileiro
        pattern = re.compile(r'^\(?[1-9]{2}\)? ?(?:9\d{4}[- ]?\d{4}|9\d{4})$')
        if pattern.match(phone_number):
            return True
        else:
            return False
    
    def is_valid_cep(cep):
        # Regex para validar o formato do CEP (XXXXX-XXX)
        pattern = re.compile(r'^\d{5}-\d{3}$')
        if pattern.match(cep):
            # Verificar se o intervalo de valores é válido (opcional)
            # Por exemplo, para o Brasil, os CEPs estão no intervalo de 01000-000 a 99999-999
            cep_digits = int(cep.replace('-', ''))
            #  01208011
            if 1000000 <= cep_digits <= 99999999:
                return True
        return False