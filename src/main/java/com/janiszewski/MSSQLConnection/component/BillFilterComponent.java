package com.janiszewski.MSSQLConnection.component;

import com.janiszewski.MSSQLConnection.entity.BillGroup;
import com.janiszewski.MSSQLConnection.entity.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BillFilterComponent {

    private Location locationSelected;
    private BillGroup billGroupSelected;

}